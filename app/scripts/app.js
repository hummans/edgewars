
function load() {
    $.ajax({
        url: '/data/data.json',
        success: function(response){
            process(response);
        }
    })
}

function process(json) {
    byTask(json);
    bySprint(json);
    byCategory(json);
    byPerson(json);
}

function byTask(json) {
    $.each(json.cards, function(i, card) {
        /*if(card.closed !== true) {
            return true;
        }*/
        var idShort = card.idShort;

        $.each(json.actions, function(i, action) {
            if(!action.data.card) {
                return true;
            }
            if(action.data.card.idShort === idShort) {
                if(action.type !== 'commentCard') {
                    return true;
                }
                card.hours = action.data.text;
                return false;
            }
        });

        if(card.hours == undefined) {
            return true;
        }

        $('#hours-by-task').append('<tr><td>' + card.name + '</td><td>' + card.hours + '</td></tr>');
    });
}

function bySprint(json) {
    var sprints = {};
    $.each(json.cards, function(i, card) {
        /*if(card.closed !== true) {
            return true;
        }*/
        var idShort = card.idShort;

        $.each(json.actions, function(i, action) {
            if(!action.data.card) {
                return true;
            }
            if(action.data.card.idShort === idShort) {
                if(action.type !== 'commentCard') {
                    return true;
                }
                card.hours = action.data.text;
                return false;
            }
        });

        $.each(card.labels, function(i, label) {
            if(label.name.substring(0, 3) === 'Spr') {
                if(isNaN(sprints[label.name])) {
                    sprints[label.name] = 0;
                }
                sprints[label.name] += parseInt(card.hours);
            }
        });
    });

    $.each(sprints, function(name, hours) {
        $('#hours-by-sprint').append('<tr><td>' + name + '</td><td>' + hours + '</td></tr>');
    });
}

function byCategory(json) {
    var sprints = {};
    $.each(json.cards, function(i, card) {
        /*if(card.closed !== true) {
            return true;
        }*/
        var idShort = card.idShort;

        $.each(json.actions, function(i, action) {
            if(!action.data.card) {
                return true;
            }
            if(action.data.card.idShort === idShort) {
                if(action.type !== 'commentCard') {
                    return true;
                }
                card.hours = action.data.text;
                return false;
            }
        });

        $.each(card.labels, function(i, label) {
            if(label.name.substring(0, 3) !== 'Spr') {
                if(isNaN(sprints[label.name])) {
                    sprints[label.name] = 0;
                }
                sprints[label.name] += parseInt(card.hours);
            }
        });
    });

    $.each(sprints, function(name, hours) {
        $('#hours-by-category').append('<tr><td>' + name + '</td><td>' + hours + '</td></tr>');
    });
}

function byPerson(json) {
    var persons = {};
    $.each(json.cards, function(i, card) {
        /*if(card.closed !== true) {
            return true;
        }*/
        var idShort = card.idShort;

        $.each(json.actions, function(i, action) {
            if(!action.data.card) {
                return true;
            }
            if(action.data.card.idShort === idShort) {
                if(action.type !== 'commentCard') {
                    return true;
                }
                card.hours = action.data.text;
                return false;
            }
        });

        $.each(json.members, function(i, member) {
            if(card.idMembers.length <= 0){
                return true;
            }

            if(member.id == card.idMembers[0]) {
                if(isNaN(persons[member.fullName])) {
                    persons[member.fullName] = 0;
                }
                persons[member.fullName] += parseInt(card.hours);
            }
        });
    });

    $.each(persons, function(name, hours) {
        $('#hours-by-person').append('<tr><td>' + name + '</td><td>' + hours + '</td></tr>');
    });
}

load();
