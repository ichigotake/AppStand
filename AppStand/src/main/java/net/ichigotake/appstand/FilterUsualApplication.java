package net.ichigotake.appstand;

class FilterUsualApplication implements Filter {

    final private String[] mPackages = {
            "net.ichigotake.appstand.free.debug",
            "net.ichigotake.appstand.pro.debug"
    };

    @Override
    public boolean apply(String packageName) {
        boolean apply = false;
        for (String filter : mPackages) {
            if (filter.equals(packageName)) {
                apply = true;
            }
        }
        return apply;
    }
}
