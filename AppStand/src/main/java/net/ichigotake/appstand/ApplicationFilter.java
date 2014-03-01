package net.ichigotake.appstand;

enum ApplicationFilter {

    USUAL,
    ALL,
    ;

    Filter createFilter() {
        final Filter filter;
        switch (this) {
            case USUAL:
                filter = new FilterUsualApplication();
                break;
            case ALL:
            default:
                filter = new FilterAllApplication();
                break;
        }
        return filter;
    }
}
