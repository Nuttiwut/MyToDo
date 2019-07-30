const express = require('express');
const server = express();
const mysql = require('mysql');
const bodyParser = require('body-parser');

server.use(bodyParser.urlencoded({
    extended: true,
}));

const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'pilab'
});
db.connect(err =>{
    if (err){
        throw err;
    }
});

server.get('/get_todo', (req, res) => {
    setTimeout(() => {
        db.query(
        'SELECT * FROM todo',
        (err, data, fields) => {
            if (err){
                res.send({
                    error: {
                        code:1,
                        massage: 'เกิดข้อผิดพลาดในการเข้าถึงฐานข้อมูล',
                        log: err.message,
                    },
                    data: null,
                });
            } else{
                for(let i=0; i < data.length; i++){
                    const item = data[i];
                    let {finished} = item;
                    finished = finished == 1;
                    item.finished = finished;
                }
                
                res.send({
                    error: {
                        code:0,
                        massage: 'อ่านข้อมูลสำเร็จ',
                        log: '',
                    },
                    data,
                });
            }

        }
    )
    }, 0)
    
});

server.post('/add_todo', (req, res) => {
    setTimeout(() => {
        db.query(
        'INSERT INTO todo (title, details) VALUES (?,?)',
        [req.body.title, req.body.details],
        (err, data, fields) => {
            if (err){
                res.send({
                    error: {
                        code:1,
                        massage: 'เกิดข้อผิดพลาดในการเข้าถึงฐานข้อมูล',
                        log: err.message,
                    }
                });
            };       
                res.send({
                    error: {
                        code:0,
                        massage: 'เพิ่มข้อมูลสำเร็จ',
                        log: '',
                    }
                });

        }
    )
    }, 0)
    
});

server.listen(3000, () => {
    console.log('Server listening at port 3000');
});