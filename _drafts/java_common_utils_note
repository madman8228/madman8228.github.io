### java中通过udp发送基本数据类型的方法
```  
InetAddress  serverAddr = InetAddress.getByName("192.168.31.6");
int port = 8000;

String cmd = "3A 2B 31 00";

byte byteCmd[] = CommonUtils.hexStringToByteArray(cmd);

byte cmd1[]  = {0x01, 0x02, 0x10};//java byte 数据类型 -128-127

//outPacket  = new DatagramPacket(cmd1, cmd1.length,serverAddr, port);

outPacket  = new DatagramPacket(byteCmd, byteCmd.length,serverAddr, port);

mSocket.send(outPacket);


public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    if (len % 2 != 0) {
        len = len - 1; //maybe should return null.
    }
    if (len <= 0) {
        return null;
    }
    byte[] data = new byte[len / 2];

    for (int i = 0; i < len; i += 2) {
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                .digit(s.charAt(i + 1), 16));
    }

    return data;
} 
```
