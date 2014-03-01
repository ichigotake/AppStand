package net.ichigotake.appstand;

class FilterProApplication implements Filter {

    final private String mPrefix = "net.ichigotake.appstand.pro";

    @Override
    public boolean apply(String name) {
        return name != null && name.startsWith(mPrefix);
    }
}
