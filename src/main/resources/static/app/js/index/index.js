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

    $('#run').click(function () {
        controller.runHarvest(function () {
            location.reload();
        })
    });

    $('#webhook_add').click(function () {
        var webhookUrl = $('#webhook_url').val();
        controller.addWebhook(webhookUrl, function () {
            location.reload()
        });
    });

    $('.rss_item_work_status').change(function () {
        var rssId = this.value
        var workStatus = this.checked
        controller.updateRssWorkStatus(rssId, workStatus, function (data) {

        });
    });

    $('.webhook_work_status').change(function () {
        var webhookId = this.value
        var workStatus = this.checked
        controller.updateWebhookStatus(webhookId, workStatus, function (data) {

        });
    })
});