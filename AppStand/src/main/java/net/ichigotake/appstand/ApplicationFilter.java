package net.ichigotake.appstand;

enum ApplicationFilter {

    USUAL,
    ALL,
    ;

    Filter createFilter() {
        final Filter filter;
        switch (this) {
            case USUAL:
                filter = new UsualApplicationFilter();
                break;
            case ALL:
            default:
                filter = new AllApplicationFilter();
                break;
        }
        return filter;
    }
}
