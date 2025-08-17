#maven_repository="MavenLocal"
maven_repository="ReleaseRepository"
# ReleaseRepository
while getopts "m:" opt; do
  case $opt in
    m)
      maven_repository=$OPTARG;;
    \?)
      ;;
  esac
done

cd ..
set -e
./gradlew :arouter-annotation:publishMavenPublicationTo"$maven_repository"
./gradlew :arouter-compiler:publishMavenPublicationTo"$maven_repository"
./gradlew :arouter-api:publishMavenPublicationTo"$maven_repository"
./gradlew :arouter-gradle-plugin:publishMavenPublicationTo"$maven_repository"