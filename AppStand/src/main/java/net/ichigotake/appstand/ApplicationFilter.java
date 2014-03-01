package net.ichigotake.appstand;

enum ApplicationFilter {

    USUAL,
    FREE,
    PRO,
    ALL,
    ;

    Filter createFilter() {
        final Filter filter;
        switch (this) {
            case USUAL:
                filter = new FilterUsualApplication();
                break;
            case FREE:
                filter = new FilterFreeApplication();
                break;
            case PRO:
                filter = new FilterProApplication();
                break;
            case ALL:
            default:
                filter = new FilterAllApplication();
                break;
        }
        return filter;
    }
}
