/**
 *
 * Created by Songdan on 2016/4/19.
 */
$(document).ready(function() {
    var url = '/Lily/WebLogServlet';
    $('#comet-frame')[0].src = url;
});

function update(data) {
    console.info(data);
    var resultArea = $('#result')[0];
    resultArea.value = resultArea.value + data + '\n';
}
