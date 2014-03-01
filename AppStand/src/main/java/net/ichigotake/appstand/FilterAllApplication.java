package net.ichigotake.appstand;

class FilterAllApplication implements Filter {

    @Override
    public boolean apply(String name) {
        return name != null && name.startsWith(BuildConfig.BASE_PACKAGE_NAME);
    }

}
