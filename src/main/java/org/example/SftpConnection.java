package org.example;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SftpConnection extends Const{
    public void GetConnect(){
        try{
            JSch jSch = new JSch();
            Session session = jSch.getSession(SFTP_USER, SFTP_HOST, Integer.valueOf(SFTP_PORT));
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(SFTP_PASSWORD);
            System.out.println("Connecting-------");
            session.connect();
            System.out.println("Established Session");

            Channel channel = session.openChannel("sftp");
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.connect();

            System.out.println("Opened sftp Channel");

            System.out.println("Copying file to Host");
            sftpChannel.put(LOCAL_PATH+"/"+FILE_NAME, SFTP_PATH);
            System.out.println("Copied file to Host");

            sftpChannel.disconnect();
            session.disconnect();
            System.out.println("Disconnected from sftp");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
