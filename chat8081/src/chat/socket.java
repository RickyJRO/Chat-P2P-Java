/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class socket extends Thread{
private final String USER_AGENT = "Mozilla/5.0";
InetAddress ER;
DatagramSocket DS;
byte bp[]=new byte[1024];
TextArea ecran=new TextArea(10,30);
socket(TextArea ta){ecran=ta;}
 @Override
public void run(){
try{DS=new DatagramSocket(8081);}
catch(IOException e){}
while(true) receiveDP();
}
public void receiveDP(){
try{
DatagramPacket DP=new DatagramPacket(bp,1024);
DS.receive(DP);
 int port=DP.getPort();
byte Payload[]=DP.getData();
int len=DP.getLength();
String res=new String(Payload,0,0,len);
    try {
        String user = sendGetRev(port);
        ecran.appendText("\n"+user+": "+res);
    } catch (Exception ex) {
        Logger.getLogger(socket.class.getName()).log(Level.SEVERE, null, ex);
    } 
 
 
}catch(IOException e){}
}
public void sendDP(String Pr,String msg,String end){
try{
    int len=msg.length();
    byte b[]=new byte[len];
    msg.getBytes(0,len,b,0);
    int porta = sendGetBind(Pr);
    try{
        ER=InetAddress.getByName(end);
        System.out.println(porta);
        DatagramPacket DP = new DatagramPacket(b,len,ER,porta);
        DS.send(DP);
    }catch(IOException e){
        
    }
}catch(Exception ex){
        Logger.getLogger(socket.class.getName()).log(Level.SEVERE, null, ex);

}
}
private String sendGetRev(int input) throws Exception {
String url = "http://localhost:29274/RevNick/webresources/bindnick?port=" + input;
URL obj = new URL(url);
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
// optional default is GET
con.setRequestMethod("GET");
//add request header
con.setRequestProperty("User-Agent", USER_AGENT);
int responseCode = con.getResponseCode();
System.out.println("\nSending 'GET' request to URL : " + url);
System.out.println("Response Code : " + responseCode);

BufferedReader in = new BufferedReader(
 new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
 response.append(inputLine);
}
in.close();
//print result
String resposta = (response.toString());
return resposta;
}

private int sendGetBind(String input) throws Exception {
String url = "http://localhost:29274/BindNick/webresources/bindnick?nick=" + input;
URL obj = new URL(url);
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
// optional default is GET
con.setRequestMethod("GET");
//add request header
con.setRequestProperty("User-Agent", USER_AGENT);
int responseCode = con.getResponseCode();
System.out.println("\nSending 'GET' request to URL : " + url);
System.out.println("Response Code : " + responseCode);

BufferedReader in = new BufferedReader(
 new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
 response.append(inputLine);
}
in.close();
//print result
int valor = Integer.parseInt(response.toString());
return valor;
}
}


