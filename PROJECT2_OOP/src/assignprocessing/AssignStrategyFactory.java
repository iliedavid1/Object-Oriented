package assignprocessing;

import enums.CityStrategyEnum;

public final class AssignStrategyFactory {
    private AssignStrategyFactory() { }
    /**
     * This method is used to create certain strategies
     * @param orderType
     * the argument used to spot the right strategy
     */
    public static AssignStrategy createStrategy(final CityStrategyEnum orderType) {
        return switch (orderType) {
            case ID -> new IdStrategy();
            case NICE_SCORE -> new NiceScoreStrategy();
            case NICE_SCORE_CITY -> new NiceScoreCityStrategy();
        };
    }
}
