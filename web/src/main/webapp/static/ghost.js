function getNextLetter() {
    if (gameState === END) {
        print('Game finished. Hit reset for new game.');
        return;
    }
    var prefix = $("#wordInput").val();
    var difficulty = $("select").val();
    $.getJSON("/strategy/" + difficulty + "/" + prefix, function(data) {
        if (data.status === 'INVALID') {
            print(prefix + " is not a valid word. I win!!");
            gameState = END;
            return;
        } else if (data.status === 'PREFIX_COMPLETE') {
            print('You completed the word. I win!!');
            gameState = END;
            return;
        }

        $("#wordInput").val(prefix + data.nextCharacter);
        if (data.status === 'COMPLETE') {
            print('I completed the word. You win!!');
            gameState = END;
        } else if (data.status === 'SUCCESS') {
            print('');
        }
    });
}

function print(text) {
    $('#result').append("<div>" + text + "</div>")
}

function reset() {
    $("#wordInput").val('');
    $('#result').html('');
    gameState = PLAYING;
}
reset();
var END = 1;
var PLAYING = 2;