const http = require("http"); 
const port = 3000; 

// กำหนดเซพเวอร์
const server = http.createServer((req, res) => { 
    // Callback
    res.statusCode = 200; 
    res.setHeader('Content-Type', 'application/json'); 
    
    // สร้างข้อมูล JSON
    const data = [];
    data.push({
        firstName: 'Nuttiwut',
        lastName : 'Thawornwong',
        age: '29',
    })
    data.push({
        firstName: 'rrrrrrr',
        lastName : 'ggggg',
        age: '55',
    })

    // ตรวจสอบการส่งข้อมูล
    const output = {
        error:{
            code: 0,
            message: 'อ่านข้อมูลสำเร็จ',
            log: '',
        },
        data,
    }
    let x = {data};

    // ในกรณีรับตัวแปลชื่อเดียวกับสิ่งที่ส่งมา

    // let data = x.data; 
    //ย่อได้เป้น let {data} = x;
    
    /*
    let data1 = x.data1;
    let data2 = x.data2;
    ย่อได้เป็น
    let {data1 ,data2} = x;
    */

    res.end(JSON.stringify(output));
}); 

// เริ่มดักฟัง
server.listen(port, () => { 
    console.log(`Server running at port ${port}`); 
});