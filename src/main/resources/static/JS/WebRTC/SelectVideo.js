/*
*  Copyright (c) 2015 The WebRTC project authors. All Rights Reserved.
*
*  Use of this source code is governed by a BSD-style license
*  that can be found in the LICENSE file in the root of the source
*  tree.
*/

'use strict';

const videoElement = document.querySelector('video');
//const audioInputSelect = document.querySelector('select#audioSource');
//const audioOutputSelect = document.querySelector('select#audioOutput');
const videoSelect = document.querySelector('select#videoSource');
const selectors = [ videoSelect];

const canvas = document.getElementById('canvas');




//audioOutputSelect.disabled = !('sinkId' in HTMLMediaElement.prototype);

function gotDevices(deviceInfos) {
    // Handles being called several times to update labels. Preserve values.
    const values = selectors.map(select => select.value);
    selectors.forEach(select => {
        while (select.firstChild) {
            select.removeChild(select.firstChild);
        }
    });
    for (let i = 0; i !== deviceInfos.length; ++i) {
        const deviceInfo = deviceInfos[i];
        const option = document.createElement('option');
        option.value = deviceInfo.deviceId;
       if (deviceInfo.kind === 'videoinput') {
            option.text = deviceInfo.label || `camera ${videoSelect.length + 1}`;
            videoSelect.appendChild(option);
        } else {
            //console.log('Some other kind of source/device: ', deviceInfo);
        }
    }
    selectors.forEach((select, selectorIndex) => {
        if (Array.prototype.slice.call(select.childNodes).some(n => n.value === values[selectorIndex])) {
            select.value = values[selectorIndex];
        }
    });
}

navigator.mediaDevices.enumerateDevices().then(gotDevices).catch(handleError);
//
// // Attach audio output device to video element using device/sink ID.
// function attachSinkId(element, sinkId) {
//     if (typeof element.sinkId !== 'undefined') {
//         element.setSinkId(sinkId)
//             .then(() => {
//                 console.log(`Success, audio output device attached: ${sinkId}`);
//             })
//             .catch(error => {
//                 let errorMessage = error;
//                 if (error.name === 'SecurityError') {
//                     errorMessage = `You need to use HTTPS for selecting audio output device: ${error}`;
//                 }
//                 console.error(errorMessage);
//                 // Jump back to first output device in the list as it's the default.
//                 audioOutputSelect.selectedIndex = 0;
//             });
//     } else {
//         console.warn('Browser does not support output device selection.');
//     }
// }
//
// function changeAudioDestination() {
//     const audioDestination = audioOutputSelect.value;
//     attachSinkId(videoElement, audioDestination);
// }

function gotStream(stream) {
    window.stream = stream; // make stream available to console
    videoElement.srcObject = stream;
    // Refresh button list in case labels have become available
    return navigator.mediaDevices.enumerateDevices();
}

function handleError(error) {
    console.log('navigator.MediaDevices.getUserMedia error: ', error.message, error.name);
}

function start() {
    if (window.stream) {
        window.stream.getTracks().forEach(track => {
            track.stop();
        });
    }
    //const audioSource = audioInputSelect.value;
    const videoSource = videoSelect.value;
    const constraints = {
        video: {
            deviceId: videoSource ? {exact: videoSource} : undefined,
            width: 1920,
            height: 1080
        }
    };
        // width: 1280,
        // height: 720
        // width: 1920,
        // height: 1080

    // const constraints = {
    //     audio: {deviceId: audioSource ? {exact: audioSource} : undefined},
    //     video: {
    //         deviceId: videoSource ? {exact: videoSource} : undefined,
    //         width: 1280,
    //         height: 720
    //     }
    // };




    navigator.mediaDevices.getUserMedia(constraints).then(gotStream).then(gotDevices).catch(handleError);
}

//audioInputSelect.onchange = start;
//audioOutputSelect.onchange = changeAudioDestination;

videoSelect.onchange = start;

start();

function capture() {

    const context = canvas.getContext('2d');

    canvas.width = videoElement.videoWidth
    canvas.height = videoElement.videoHeight;

    context.drawImage(videoElement, 0, 0, videoElement.videoWidth, videoElement.videoHeight);

    let test = canvas.toDataURL('/image/png');

    let arr = test.split("data:image/png;base64,");
    // console.log(arr[1])
    fetch("http://localhost:5000/barcode",{
        method:"POST",
        headers:{
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
           url:arr[1]
        }),
    })
        .then(res=>res.json())
        .then((data)=>{
            console.log(data);
            if(!data.isEmpty && !Object.keys(data).includes('error'))
                code(data);


            else
                console.log("없다");
        });
}

async function code(code) {
    fetch("http://localhost:8080/api/find",{
        method:"POST",
        headers:{
            "Content-Type": "application/json",
        },
        body: JSON.stringify(code),
    })
        .then(res => res.json())
        .then(res =>{
            console.log(res);
            for(let i=0;i<res.length;i++){

                if( res[i].barcode != undefined && barcodeList.indexOf(res[i].barcode) == -1 ){
                    list++;
                    barcodeList.push(res[i].barcode);

                    tabledata.push({
                        품목기준코드:res[i].productCode,
                        표준코드명:res[i].barcode,
                        제품명:res[i].pillName,
                        업체명:res[i].manName
                    })

                    // let divNode = document.createElement("div");
                    // let node = document.createElement("h3");
                    // let textnode = document.createTextNode("- 품목 기준 코드 :"+res[i].productCode+" | "+"표준 코드명 :"+res[i].barcode+" | "+"제품명 :"+res[i].pillName+" | "+"업체명 :"+res[i].manName);
                    //
                    //
                    //
                    // node.appendChild(textnode);
                    // divNode.appendChild(node);
                    //
                    //
                    // let drugCnt = document.createElement("input");
                    // drugCnt.setAttribute("name","drug[]");
                    // divNode.appendChild(drugCnt);
                    //
                    // mainTag.appendChild(divNode);






                    // console.log(list);
                }
            }
            table.replaceData(tabledata);
            document.getElementById("total-row").textContent = table.getRows().length;



        });

}

// var tabledata = [
//     {id:1, name:"Oli Bob", age:"12", col:"red", dob:""},
//     {id:2, name:"Mary May", age:"1", col:"blue", dob:"14/05/1982"},
//     {id:3, name:"Christine Lobowski", age:"42", col:"green", dob:"22/05/1982"},
//     {id:4, name:"Brendon Philips", age:"125", col:"orange", dob:"01/08/1980"},
//     {id:5, name:"Margret Marmajuke", age:"16", col:"yellow", dob:"31/01/1999"},
// ];

var tabledata = [];


var table = new Tabulator("#example-table", {
    height:"311px",
    addRowPos:"bottom",
    layout:"fitColumns",
    data:tabledata,
    columns:[
        {title:"품목기준코드", field:"품목기준코드", width:200,},
        {title:"표준코드명", field:"표준코드명", width:200, },
        {title:"제품명", field:"제품명", width:200, },
        {title:"업체명", field:"업체명", width:200, },
    ],
});



//Add row on "Add Row" button click
document.getElementById("add-row").addEventListener("click", function(){
    table.addRow({});
});

//Delete row on "Delete Row" button click
document.getElementById("del-row").addEventListener("click", function(){
    table.deleteRow(1);
});

//Clear table on "Empty the table" button click
document.getElementById("clear").addEventListener("click", function(){
    table.clearData()
});

//Reset table contents on "Reset the table" button click
document.getElementById("reset").addEventListener("click", function(){
    table.setData(tabledata);
});



 // function f() {
 //     setInterval(capture,10);
 // }
function initialize() {


    setInterval(capture,3000)


}

let isStop = false;



let list=0;
let barcodeList=[];
document.querySelector('#btn-capture').addEventListener('click', capture)


// initialize();