import java.io.*;
import java.net.*;
public class Client {
    public static void main(String arg[])throws Exception{
        try {
            Socket s = new Socket("127.0.0.1", 3000);
			System.out.println("Connected");
			DataInputStream din=new DataInputStream(s.getInputStream());
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String str="",str2="";
			while(!str.equals("stop")){
				str=br.readLine();
				dout.writeUTF(str);
				dout.flush();
				str2=din.readUTF();
				System.out.println("Server says:"+str2);
			}
			dout.close();
			s.close();
        }catch (Exception e){System.out.println(e);}
    }
}