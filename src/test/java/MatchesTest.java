public class MatchesTest {
    public static void main(String[] args) {
        String regx = "gid\\d+";
        System.out.println("gid33".matches(regx));
        System.out.println(Integer.parseInt("gid33".substring(3)));
    }
}
