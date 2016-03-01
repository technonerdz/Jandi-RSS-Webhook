require(['material', '/app/js/index/index_controller.js'], function (material, controller) {
    $(function () {
        $.material.init();
    });

    var elRssUrl = $('#rss_url');
    var elRssAdd = $('#rss_add');

    elRssAdd.click(function () {
        controller.addRSS(elRssUrl.val(), function () {
            location.reload();
        });
    });

    $('#webhook_manage').click(function () {
        $('#webhook-modal').modal()
    });

    $('#webhook_add').click(function () {
        var webhookUrl = $('#webhook_url').val();
        controller.addWebhook(webhookUrl, function () {
            location.reload()
        });
    });

    $('.rss_item').click(function (event) {
        var rssId = event.currentTarget.id;
        controller.getRSS(rssId, function (data) {
            $('#rss-modal-title').text(data.name);
            $('#rss-modal-url').text(data.rssUrl);
            $('#rss-modal-delete').click(function () {
                controller.deleteRSS(rssId, function () {
                    location.reload();
                });
            });
            $('#rss-modal').modal();
        });
    });

});