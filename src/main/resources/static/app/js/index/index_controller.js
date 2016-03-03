/**
 * Created by jsuch2362 on 2016. 3. 1..
 */
define([], function () {
    return {
        addRSS: function (url, callback) {
            $.ajax({
                url: '/rss',
                type: 'POST',
                data: JSON.stringify({
                    rssUrl: url
                }),
                headers: {
                    'Content-Type': "application/json"
                },
                success: callback
            })
        },

        getRSS: function (id, callback) {
            $.ajax({
                url: '/rss/' + id,
                type: 'GET',
                success: callback
            })
        },
        deleteRSS: function (id, callback) {
            $.ajax({
                url: '/rss/' + id,
                type: 'DELETE',
                success: callback
            })
        },
        addWebhook: function (url, callback) {
            $.ajax({
                url: '/webhook',
                type: 'POST',
                data: JSON.stringify({
                    url: url
                }),
                headers: {
                    'Content-Type': "application/json"
                },
                success: callback
            })
        },
        getWebhooks: function (callback) {
            $.ajax({
                url: '/webhook',
                type: 'GET',
                success: callback
            })
        },
        updateRssWorkStatus: function (rssId, workStatus, callback) {
            $.ajax({
                url: '/rss/' + rssId + "/work",
                type: 'PUT',
                data: JSON.stringify({
                    work: workStatus
                }),
                headers: {
                    'Content-Type': "application/json"
                },
                success: callback
            })
        },
        updateWebhookStatus: function (rssId, workStatus, callback) {
            $.ajax({
                url: '/webhook/' + rssId + "/work",
                type: 'PUT',
                data: JSON.stringify({
                    work: workStatus
                }),
                headers: {
                    'Content-Type': "application/json"
                },
                success: callback
            })
        },
        runHarvest: function (callback) {
            $.ajax({
                url: '/rss/harvest',
                success: callback
            })

        }
    }
});