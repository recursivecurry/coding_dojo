var empty = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];

var orderUp = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15];
var orderDown = [15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0];
var orderRight = [3,7,11,15,2,6,10,14,1,5,9,13,0,4,8,12];
var orderLeft = [0,4,8,12,1,5,9,13,2,6,10,14,3,7,11,15];

exports.Game = function () {

    var board = empty.slice(0);

    function add () {
        var idx,
            zeros = [],
            newval = [];

        for (idx in board) {
            if (0 === board[idx])
                zeros.push(idx);
        }

        newval.push(zeros[Math.floor(Math.random()*zeros.length)%zeros.length]);
        newval.push(Math.random() > 0.5 ? 2 : 4);
        board[newval[0]] = newval[1];

        //console.log("add: ", newval);
        return newval;
    }

    function move (src, offset) {
        var pos;
        var dst = src,
            val = board[src],
            result = [],
            bottom,
            top;

        if (0===val)
            return [];

        if (4===offset || -4===offset) {
            bottom = 0;
            top = 15;
        } else {
            bottom = src - (src%4);
            top = bottom + 3;
        }
        for(pos = src+offset; ; pos+=offset) {
            if (pos<bottom || top<pos) {
                if (dst !== src) {
                    board[dst] = val;
                    board[src] = 0;
                    result = [[src, val, dst, val]];
                }
                break;
            } else if (0 !== board[pos]) {
                if (board[pos]===val) {
                    board[pos] = val * 2;
                    board[src] = 0;
                    result = [[src, val, pos, val*2]];
                } else {
                    if (dst!==src) {
                        board[dst] = val;
                        board[src] = 0;
                        result = [[src, val, dst, val]];
                    }
                }
                break;
            }
            dst = pos;
        }
        //console.log("move: "+result);
        return result;
    }

    function check () {
        var result = [true];
        if (-1 !== board.indexOf(0))
            result[0] = false;
        result.push(Math.max.apply(null, board));

        //console.log('check: ', result);
        return result;
    }

    this.new = function () {
        board = empty.slice(0);
        add();
    };

    this.move = function (key) {
        var idx,
            order,
            offset,
            result = [];

        if (key === 'k') {
            order = orderUp;
            offset = -4;
        }
        else if (key === 'l') {
            order = orderRight;
            offset = 1;
        }
        else if (key === 'j') {
            order = orderDown;
            offset = 4;
        }
        else if (key === 'h') {
            order = orderLeft;
            offset = -1;
        }

        if (order) {
            for (idx in order)
                result = result.concat(move(order[idx], offset));
            if (0<result.length) {
                add();
            }
        }
    };

    this.show = function () {
        var y;

        for (y=0; y<4; y++) {
            console.log(board[y*4], board[y*4+1], board[y*4+2], board[y*4+3]);
        }
    };

    this.board = function () {
        return board.slice(0);
    }

    {   // initialize
        add();
    }
};