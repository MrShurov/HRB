$(document).ready(function(){
    $('#addEvent').click(function () {
        addEvent();
    });
   /* $('#updateEvent').click(function () {
        updateEvent();
    });*/
});

function addEvent() {
    var team1 = $("#team1").val();
    var team2 = $("#team2").val();
    if (team1 && team2) {
        var event = {
            team1 : team1,
            team2 : team2
        };
        $.ajax({
            data:event,
            dataType: "json",
            type:'post',
            url: restUrl
        }).done(function(data) {
            var el = '<div>Event created data.team1 data.team2</div>';
            $(".eventTable").append(el);
        }).fail(function(data){
            var el = '<div>Error data.team1 data.team2 </div>';
            $(".eventTable").append(el);
        });
    }
}

/*function updateEvent() {
    var id = $("#id").val();
    var eventResult = $("#eventResult").val();
    if (id && eventResult) {
        var event = {
            id : id,
            eventResult : eventResult
        };
        $.ajax({
            data:event,
            dataType: "json",
            type:'put',
            url: ${pageContext.request.contextPath}/events/ + id
        }).done(function(data) {
            var el = '<div>Event updated data.team1 data.team2</div>';
            $(".updateEventTable").append(el);
        }).fail(function(data){
            var el = '<div>Error data.team1 data.team2 </div>';
            $(".updateEventTable").append(el);
        });
    }
}*/
