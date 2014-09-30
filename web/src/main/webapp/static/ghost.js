function getNextLetter() {
    disableButton();
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
        }
    });
}

function print(text) {
    $('#result').append("<div>" + text + "</div>")
}

var reset = function() {
    $("#wordInput").val('');
    $('#result').html('');
    if (gameState != INIT) {
        enableButton();
    }
    gameState = PLAYING;
};

$(document).ready(function() {
    reset();
    $('#wordInput').keyup(function() {
        enableButton();
    });
    $('#nextLetter').click(getNextLetter);
});

var disableButton = function() {
    $('#nextLetter').prop('disabled', true);
};

var enableButton = function() {
    $('#nextLetter').removeAttr('disabled');
};

var INIT = 0;
var END = 1;
var PLAYING = 2;
var gameState = INIT;
