/**
 * Created by Songdan on 2017/1/4.
 */
function objClone(obj) {
    var newObject = {};
    for (var prop in obj) {
        if (obj.hasOwnProperty(prop)) {
            newObject[prop] = cloneProp(obj[prop]);
        }
    }
    return newObject;

    function cloneProp(value) {
        var type = Object.prototype.toString.apply(value).slice(8, -1);
        switch (type) {
            case 'Number':
            case 'String':
                return value;
            case 'Object':
                return objClone(value);
            case 'Array':
                var array = [];
                for (var i = 0; i < value.length; i++) {
                    array[i] = cloneProp(value[i]);
                }
                return array;
        }
    }
}

