import java.util.Map;
import java.util.Scanner;

public class App {

    static User user;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int menu;
        String bannedName[] = { "새로", "새로고침", "생존신고", "친구코드", "코드", "친구", "생존신고", "생존", "프로필", "친구추가", "추가" };

        // test를 위한 더미 유저 정보
        user = new User("test", "test", "테스터");
        User user2 = new User("a", "a", "홍길동");
        User user3 = new User("b", "b", "이춘향");
        User.users.put("test", user);
        User.users.put("a", user2);
        User.users.put("b", user3);
        user.setFriends(user2);
        user.setMessage(user3);

        login();
        load();

        while (true) {

            for (String fri : user.getSRRequire()) {
                System.out.println(fri + "님에게 생존 신고 요청!!");
            }

            user.getSRRequire().clear();
    
            for (Map.Entry<String, User> mem : user.getMessage().entrySet()) {
                if (User.users.containsKey(mem.getKey())) {
                    System.out.print(mem.getValue().getName() + "님의 친구요청 도착! (수락/거절) > ");
                    String ans = sc.nextLine();
                    if (ans.equals("수락") || ans.equals("y") || ans.equals("Y")) {
                        user.setFriends(mem.getValue());
                        user.getMessage().remove(mem.getKey());
                        User.users.get(mem.getValue().getId()).setFriends(user);
                        load();
                        System.out.println(mem.getValue().getName() + "님이 추가되었습니다.");
                    } else {
                        user.getMessage().remove(mem.getKey());
                        System.out.println(mem.getValue().getName() + "님의 요청을 거절하였습니다.");
                    }
                }
            }

            System.out.print("> ");
            String ex = sc.nextLine(); // 새로고침, 친구코드, 생존신고, 프로필편집, 친구추가, 친구이름
            switch (ex) {
                case "새로고침", "새로":
                    load();
                    break;
                case "친구코드", "코드":
                    load();
                    System.out.println("친구코드(복사해서 쓰세요): " + user.getCode());
                    break;
                case "생존신고", "신고", "생존":
                    user.setLastSR();
                    load();
                    System.out.println(user.getLastSR() + "로 갱신되었습니다.");
                    break;
                case "프로필":
                    System.out.print("변경할 이름을 입력하세요.(기존: " + user.getName() + ") > ");
                    String name = sc.nextLine();
                    user.setName(name);
                    load();
                    System.out.println(name + "으로 변경되었습니다.");
                    //
                    break;
                case "친구추가", "추가":
                    System.out.print("친구의 코드를 입력하세요: ");
                    int code;
                    try {
                        code = Integer.parseInt(sc.nextLine());
                        boolean valid = false;
                        for (Map.Entry<String, User> entry : User.users.entrySet()) {
                            if (entry.getValue().getCode() == code) {
                                entry.getValue().setMessage(user);
                                System.out.println(entry.getValue().getName() + "님에게 친구요청 전송 완료.");
                                valid = true;
                            }
                        }
                        if (!valid) {
                            System.out.println("유효하지 않은 코드입니다.");
                        }
                    } catch (Exception e) {
                        System.out.println("유효하지 않은 입력입니다.");
                    }

                    //
                    break;
                case "로그아웃", "logout":
                    System.out.println("로그아웃 되었습니다!");
                    login();
                    load();
                    break;
                default: // 친구이름 or 잘못된 입력
                    boolean isFriend = false;
                    for (Map.Entry<String, User> friend : user.getFriends().entrySet()) {

                        if (friend.getValue().getName().equals(ex)) {

                            isFriend = true;

                            System.out.print(friend.getValue().getName() + "님에게 신고 [요청] / 님을 [삭제] > ");
                            String what = sc.nextLine();
                            if (what.equals("요청")) {
                                friend.getValue().setSRRequire(user.getName());
                                load();
                                System.out.println(friend.getValue().getName() + "님에게 생존 신고 요청 완료.");
                            } else if (what.equals("삭제")) {
                                System.out.print(ex + "님을 삭제하시겠습니까? (y/n)");
                                String ans = sc.nextLine();
                                if (ans.equals("y") || ans.equals("Y") || ans.equals("yes") || ans.equals("YES")) {
                                    User.users.get(friend.getKey()).getFriends().remove(user.getId());
                                    user.getFriends().remove(friend.getKey());
                                    load();
                                    System.out.println(ex + "님을 삭제하였습니다.");
                                } else
                                    load();

                            } else
                                System.out.println("잘못된 입력입니다.");
                        }
                        
                    }
                    if (!isFriend)
                        System.out.println("잘못된 입력입니다.");
                    break;
            }

        }
    }

    public static void login() {

        int menu;

        while (true) {
            System.out.print("1: 로그인 2: 회원가입 > ");
            menu = Integer.parseInt(sc.nextLine());
            if (menu == 1) {
                String id;
                String pw;
                System.out.print("id: ");
                id = sc.nextLine();
                System.out.print("pw: ");
                pw = sc.nextLine();
                if (User.users.containsKey(id)) {
                    if (User.users.get(id).pw.equals(pw)) {
                        user = User.users.get(id);
                        System.out.println(user.name + "님 반갑습니다!");
                        break;
                    } else {
                        System.out.println("pw가 다릅니다.");
                    }
                } else {
                    System.out.println("존재하지 않는 id입니다.");
                }

            } else if (menu == 2) {
                String id;
                String pw;
                String name;
                System.out.print("id: ");
                id = sc.nextLine();
                while (User.users.containsKey(id)) {
                    System.out.print("중복된 id입니다.\nid: ");
                    id = sc.nextLine();
                }
                System.out.print("pw: ");
                pw = sc.nextLine();
                System.out.print("name: ");
                name = sc.nextLine();
                User.users.put(id, new User(id, pw, name));
                System.out.println(name + "님 환영합니다! 다시 로그인해주세요.");
            }
        }

    }

    public static void load() {
        StringBuilder sb = new StringBuilder();

        sb.append("~----------------------------------------~" + "\n");
        sb.append("|  괄호 안의 내용을 입력하면 실행됩니다. |\n");
        sb.append("|*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-|\n");
        sb.append("| [새로고침]                  [친구코드] |" + "\n");
        // sb.append("| [생존신고] |" + "\n");
        sb.append(String.format("                 %3s 님                   \n", user.getName()));
        sb.append("|               [생존신고]               |\n");
        sb.append("|            마지막 생존신고일           |\n");
        sb.append("|          " + user.lastSR + "           |\n");
        sb.append("~----------------------------------------~\n");
        sb.append("| [프로필]      [로그아웃]    [친구추가] |\n");
        sb.append("|*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-|\n");

        for (Map.Entry<String, User> friend : user.getFriends().entrySet()) {
            sb.append(String.format("|     [%3s] %s       |\n", friend.getValue().getName(),
                    friend.getValue().getLastSR()));
        }
        sb.append("~----------------------------------------~");

        System.out.println(sb);
    }

}
