import java.io.*;
import java.net.*;
public class Server {
    public static void main(String arg[]) throws Exception{
        try {
            ServerSocket server = new ServerSocket(3000);
            Socket s = server.accept(); //realizam conexiunea
			System.out.println("Connected");
			DataInputStream din=new DataInputStream(s.getInputStream());
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String str="",str2="";
			while(!str.equals("stop")){
				str=din.readUTF();
				System.out.println("Client says: "+str);
				str2=br.readLine();
				dout.writeUTF(str2);
				dout.flush();
			}
			din.close();
			s.close();
			server.close();
        }catch(Exception e){System.out.println(e);}
    }
}