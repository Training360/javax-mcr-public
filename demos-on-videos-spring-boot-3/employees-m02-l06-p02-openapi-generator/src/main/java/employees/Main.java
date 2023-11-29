package employees;

public class Main {

    private String anme;
    private int b;

    public static void main(String[] args) {
        String s = "ab";
        System.out.println(s.hashCode());

        System.out.println(new Main().hashCode());
        System.out.println(new Main());
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Main)) return false;
        final Main other = (Main) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$anme = this.anme;
        final Object other$anme = other.anme;
        if (this$anme == null ? other$anme != null : !this$anme.equals(other$anme)) return false;
        if (this.b != other.b) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Main;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $anme = this.anme;
        result = result * PRIME + ($anme == null ? 43 : $anme.hashCode());
        result = result * PRIME + this.b;
        return result;
    }
}
