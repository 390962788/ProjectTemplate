/*
package com.guoyizeng.xmpp;

import android.util.Log;

import java.util.TimerTask;

import static android.content.ContentValues.TAG;

*/
/**
 * Created by Administrator on 2016/11/1.
 *//*


public class XmppUtil {
    XmppConnecionListener xmppConnecionListener;
    private void connetionXmpp() {
        xmppConnecionListener = new XmppConnecionListener();

        ProviderManager.getInstance().addIQProvider("notification", "androidpn:iq:notification",new NotificationIQProvider());
        PacketFilter packetFilterNotification = new PacketTypeFilter(
                NotificationIQ.class);
        PacketFilter packetFilterMessage = new PacketTypeFilter(
                org.jivesoftware.smack.packet.Message.class);
        orfilter = new OrFilter(packetFilterNotification, packetFilterMessage);

        packetListener = new NotificationPacketListener(this);
        try {
            new Thread() {
                public void run() {
                    try {
                        conXmpp();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception e) {
            Log.i("connetionXmpp", e.toString());
        }
    }


    private void conXmpp() {
        try {
            XMPPConnection xmppConnection = XmppTool.getConnection();
            Log.i(TAG,
                    "xmppConnection.isConnected()"
                            + xmppConnection.isConnected());
            if (xmppConnection.isConnected()) {
                xmppConnection.addConnectionListener(xmppConnecionListener);
                xmppConnection.addPacketListener(packetListener, orfilter);
                Log.i(TAG, "xmppConnection packetListener success");

                FileTransFerListener(xmppConnection);

                xmppConnection.login(xmppUsername, xmppPwd, RESOURCE);
                Log.i(TAG, "xmppConnection.login success");
            } else {
                tExit = new Timer();
                tExit.schedule(new XmppTimetask(), xmppSpace);
            }
        } catch (XMPPException e) {
            Log.i("connetionXmpp", e.toString());
        }
    }



    private void FileTransFerListener(XMPPConnection xmppConnection) {
        // FileTransfer
        ProviderManager pm = ProviderManager.getInstance();
        pm.addIQProvider("si", "http://jabber.org/protocol/si",
                new StreamInitiationProvider());
        pm.addIQProvider("query", "http://jabber.org/protocol/bytestreams",
                new BytestreamsProvider());
        pm.addIQProvider("query", "http://jabber.org/protocol/disco#items",
                new DiscoverItemsProvider());
        pm.addIQProvider("query", "http://jabber.org/protocol/disco#info",
                new DiscoverInfoProvider());

        ServiceDiscoveryManager serviceDiscoveryManager = ServiceDiscoveryManager
                .getInstanceFor(xmppConnection);
        if (serviceDiscoveryManager == null)
            serviceDiscoveryManager = new ServiceDiscoveryManager(
                    xmppConnection);
        serviceDiscoveryManager
                .addFeature("http://jabber.org/protocol/disco#info");
        serviceDiscoveryManager.addFeature("jabber:iq:privacy");
        FileTransferManager managerListner = new FileTransferManager(
                xmppConnection);
        managerListner.addFileTransferListener(new FileTransferListenerImp(
                getApplicationContext(), handler));
        Log.i("File transfere manager", "created");
        Log.i(TAG, "xmppConnection fileTransferListener success");
    }

    private class XmppConnecionListener implements ConnectionListener {
        @Override
        public void connectionClosed() {
            try {
                disConnetionXmpp();
            } catch (Exception e) {
                Log.i("connectionClosed", e.toString());
            } finally {
                tExit = new Timer();
                tExit.schedule(new XmppTimetask(), xmppSpace);
            }
        }

        @Override
        public void connectionClosedOnError(Exception e) {
            Log.i("connection", "connectionClosedOnError");
            Log.i("connection--", e.toString());

            boolean error = e.getMessage().equals("stream:error (conflict)");
            if (!error) {
                try {
                    disConnetionXmpp();
                } catch (Exception e2) {
                    Log.i("connectionClosedOnError", e2.toString());
                } finally {
                    tExit = new Timer();
                    tExit.schedule(new XmppTimetask(), xmppSpace);
                }

            }
        }

        @Override
        public void reconnectingIn(int seconds) {
            Log.i("connection", "reconnectingIn");
        }

        @Override
        public void reconnectionSuccessful() {
            Log.i("connection", "reconnectionSuccessful");
        }

        @Override
        public void reconnectionFailed(Exception e) {
            Log.i("connection", "reconnectionFailed");
        }

    }


    private class XmppTimetask extends TimerTask {

        public XmppTimetask() {
        }

        @Override
        public void run() {
            try {
                conXmpp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
*/
