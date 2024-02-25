cd posttest
export SPRING_PROFILES_ACTIVE=test
./gradlew test spotlessCheck --stacktrace