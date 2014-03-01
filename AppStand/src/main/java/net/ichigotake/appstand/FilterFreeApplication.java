package net.ichigotake.appstand;

class FilterFreeApplication implements Filter {

    final private String mPrefix = "net.ichigotake.appstand.free";

    @Override
    public boolean apply(String name) {
        return name != null && name.startsWith(mPrefix);
    }
}
