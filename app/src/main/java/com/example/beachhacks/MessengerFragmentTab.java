package com.example.beachhacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.beachhacks.dummy.DummyContent;
import com.sendbird.android.*;
import com.sendbird.android.model.*;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessengerFragmentTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessengerFragmentTab extends Fragment {
    /******************************
     * SENDBIRD VARIABLES    *
     ******************************/
    private UserListQuery userListQuery;
    private MessagingChannelListQuery channelListQuery;
    private ArrayAdapter adapter;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    public MessengerFragmentTab() {
        // default empty constructor
    }

    public static String generateDeviceUUID(Context context) {
        String serial = android.os.Build.SERIAL;
        String androidID = Settings.Secure.ANDROID_ID;
        String deviceUUID = serial + androidID;

        MessageDigest digest;
        byte[] result;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            result = digest.digest(deviceUUID.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02X", b));
        }

        return sb.toString();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MessengerFragmentTab.
     */
    // TODO: Rename and change types and number of parameters
    public static MessengerFragmentTab newInstance(Bundle args) {
        MessengerFragmentTab fragment = new MessengerFragmentTab();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup sendbird
        SendBird.init(getContext(), getString(R.string.sendbird_app_id));
        String userID = getArguments().getString("id");
        if (userID == null)
            userID = generateDeviceUUID(getContext());
        SendBird.login(SendBird.LoginOption.build(userID));
        SendBird.setEventHandler(new MessendbirdEventHandler());
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_chat_list, container, false);

        // setContentView(R.layout.activity_chat_list);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        // TODO: setSupportActionBar(toolbar);
        toolbar.setTitle("TEST"); // getTitle());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = view.findViewById(R.id.chat_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (view.findViewById(R.id.chat_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /******************************
     * SENDBIRD METHODS      *
     ******************************/
    private void loadUsers() {
        userListQuery = SendBird.queryUserList();
        userListQuery.setLimit(30);

        if (userListQuery != null && userListQuery.hasNext() && !userListQuery.isLoading()) {
            userListQuery.next(new UserListQuery.UserListQueryResult() {
                @Override
                public void onResult(List<User> users) {
                    adapter.addAll(users);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(SendBirdException e) {

                }
            });
        }
    }

    private void inviteUser(String channelUrl, String userId) {
        SendBird.inviteMessaging(channelUrl, userId);
    }

    private void startMessaging(String userId) {
        SendBird.startMessaging(userId); // Start a 1:1 messaging with given userId.
    }

    private void startGroupMessaging(Collection<String> userIds) {
        SendBird.startMessaging(userIds); // Start a group messaging with given userIds.
    }

    private void endMessaging(String channelUrl) {
        SendBird.endMessaging(channelUrl); // Remove a messaging from my list with given channelUrl.
    }

    private void queryMessagingChannels() {
        channelListQuery = SendBird.queryMessagingChannelList();
        if (channelListQuery.hasNext()) {
            channelListQuery.next(new MessagingChannelListQuery.MessagingChannelListQueryResult() {
                @Override
                public void onResult(List<MessagingChannel> channels) {
                    adapter.addAll(channels);
                    if (channels.size() <= 0) {
                        Toast.makeText(getActivity(), "No messaging channels were found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(int errorCode) {
                    // TODO: Handle error code
                    switch (errorCode) {

                    }
                }
            });
        }
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_list_content, parent, false);
            return new SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ChatDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ChatDetailFragment fragment = new ChatDetailFragment();
                        fragment.setArguments(arguments);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.chat_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ChatDetailActivity.class);
                        intent.putExtra(ChatDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

    private class MessendbirdEventHandler implements SendBirdEventHandler {

        @Override
        public void onConnect(Channel channel) {

        }

        @Override
        public void onError(int i) {

        }

        @Override
        public void onChannelLeft(Channel channel) {

        }

        @Override
        public void onMessageReceived(Message message) {

        }

        @Override
        public void onMutedMessageReceived(Message message) {

        }

        @Override
        public void onSystemMessageReceived(SystemMessage systemMessage) {

        }

        @Override
        public void onBroadcastMessageReceived(BroadcastMessage broadcastMessage) {

        }

        @Override
        public void onFileReceived(FileLink fileLink) {

        }

        @Override
        public void onMutedFileReceived(FileLink fileLink) {

        }

        @Override
        public void onReadReceived(ReadStatus readStatus) {
            // TODO: update view so status may be seen as read
        }

        @Override
        public void onTypeStartReceived(TypeStatus typeStatus) {
            // TODO: update a view to show a message is being written
        }

        @Override
        public void onTypeEndReceived(TypeStatus typeStatus) {
            // TODO: update a view to show a message is no longer being written
        }

        /**
         * @param sendBirdDataType
         * @param i
         * @deprecated
         */
        @Override
        public void onAllDataReceived(SendBird.SendBirdDataType sendBirdDataType, int i) {

        }

        @Override
        public void onMessageDelivery(boolean b, String s, String s1, String s2) {

        }

        @Override
        public void onMessagingStarted(MessagingChannel messagingChannel) {
            SendBird.join(messagingChannel.getUrl());
            SendBird.connect();
        }

        @Override
        public void onMessagingUpdated(MessagingChannel messagingChannel) {
            // If previous channel was group messaging channel. MessagingChannel has been updated.
            // updasteCurrentChannelDisplayInfomation(channel);
        }

        @Override
        public void onMessagingEnded(MessagingChannel messagingChannel) {

        }

        @Override
        public void onAllMessagingEnded() {

        }

        @Override
        public void onMessagingHidden(MessagingChannel messagingChannel) {

        }

        @Override
        public void onAllMessagingHidden() {

        }
    }
}
