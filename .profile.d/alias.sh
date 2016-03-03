function rss() {
    command curl -v http://$HEROKU_APP_NAME.herokuapp.com/rss/harvest
}
function rss_localhost() {
    command curl -v http://localhost/rss/harvest
}