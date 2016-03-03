function rss() {
    command curl -v http://"'"$HEROKU_APP_NAME"'".herokuapp.com/rss/harverst
}