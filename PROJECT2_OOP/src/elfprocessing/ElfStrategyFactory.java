package elfprocessing;

import enums.ElvesType;

public final class ElfStrategyFactory {
    private ElfStrategyFactory() { }
    /**
     * This method is used to create certain strategies
     * @param elfType
     * the argument used to spot the right strategy
     */
    public static ElfStrategy createStrategy(final ElvesType elfType) {
        return switch (elfType) {
            case PINK -> new PinkElfStrategy();
            case BLACK -> new BlackElfStrategy();
            case WHITE -> new WhiteElfStrategy();
            case YELLOW -> new YellowElfStrategy();
        };
    }
}
