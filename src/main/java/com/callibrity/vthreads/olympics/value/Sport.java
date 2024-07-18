package com.callibrity.vthreads.olympics.value;

public enum Sport {
    ARCHERY("Archery"),
    ATHLETICS("Athletics"),
    BADMINTON("Badminton"),
    BASKETBALL("Basketball"),
    BASKETBALL_3X3("Basketball 3x3"),
    BEACH_VOLLEYBALL("Beach Volleyball"),
    BOXING("Boxing"),
    BREAKDANCING("Breakdancing"),
    CANOE_SLALOM("Canoe Slalom"),
    CANOE_SPRINT("Canoe Sprint"),
    CYCLING_BMX_FREESTYLE("Cycling BMX Freestyle"),
    CYCLING_BMX_RACING("Cycling BMX Racing"),
    CYCLING_MOUNTAIN_BIKE("Cycling Mountain Bike"),
    CYCLING_ROAD("Cycling Road"),
    CYCLING_TRACK("Cycling Track"),
    DIVING("Diving"),
    EQUESTRIAN("Equestrian"),
    FENCING("Fencing"),
    FIELD_HOCKEY("Field Hockey"),
    FOOTBALL("Football"),
    GOLF("Golf"),
    GYMNASTICS_ARTISTIC("Gymnastics Artistic"),
    GYMNASTICS_RHYTHMIC("Gymnastics Rhythmic"),
    HANDBALL("Handball"),
    HOCKEY("Hockey"),
    JUDO("Judo"),
    KARATE("Karate"),
    MARATHON_SWIMMING("Marathon Swimming"),
    MODERN_PENTATHLON("Modern Pentathlon"),
    ROWING("Rowing"),
    RUGBY_SEVENS("Rugby Sevens"),
    SAILING("Sailing"),
    SHOOTING("Shooting"),
    SKATEBOARDING("Skateboarding"),
    SPORT_CLIMBING("Sport Climbing"),
    SURFING("Surfing"),
    SWIMMING("Swimming"),
    TABLE_TENNIS("Table Tennis"),
    TAEKWONDO("Taekwondo"),
    TENNIS("Tennis"),
    TRIATHLON("Triathlon"),
    VOLLEYBALL("Volleyball"),
    WATER_POLO("Water Polo"),
    WEIGHTLIFTING("Weightlifting"),
    WRESTLING("Wrestling");

    private final String name;

    Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
