import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

class User {

    static HashMap<String, User> users = new HashMap<>(); // id, user

    static int codeCount = 0;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String id;
    String pw;
    String name;
    int code;
    String lastSR;
    HashMap<String, User> friends;
    HashMap<String, User> Message;
    HashSet<String> SRRequire;

    // ObjectMapper mapper = new ObjectMapper();

    public User(String id, String pw, String name) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        this.id = id;
        this.pw = pw;
        this.name = name;
        code = random.nextInt(10000000) + codeCount++;
        lastSR = dateFormat.format(System.currentTimeMillis());
        friends = new HashMap<>();
        Message = new HashMap<>();
        SRRequire = new HashSet<>();
        // usersCode.add(code);
    }

    public void updateFile(){
        // try (FileWriter fw = new FileWriter("./users.txt", true);
        //         BufferedWriter bw = new BufferedWriter(fw)) {
        //     bw.newLine(); // 새 줄 추가
        //     bw.write(""); // 텍스트 추가
        //     System.out.println("파일에 텍스트가 성공적으로 추가되었습니다.");
        // } catch (IOException e) {
        //     System.err.println("파일에 쓰는 동안 오류가 발생했습니다: " + e.getMessage());
        // }
    }
    public String getId() {
        return this.id;
    }

    public String getPw() {
        return this.pw;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLastSR() {
        return this.lastSR;
    }

    public void setLastSR() {
        this.lastSR = dateFormat.format(System.currentTimeMillis());
    }

    public HashMap<String, User> getFriends() {
        return this.friends;
    }

    public void setFriends(User user) {
        this.friends.put(user.getId(), user);
    }

    public HashMap<String, User> getMessage() {
        return this.Message;
    }

    public void setMessage(User user) {
        this.Message.put(user.getId(), user);
    }

    public HashSet<String> getSRRequire() {
        return this.SRRequire;
    }

    public void setSRRequire(String name) {
        this.SRRequire.add(name);
    }
}