function rss() {
    command env
    command echo "==================="
    command curl https://localhost:$PORT/rss/harverst
}