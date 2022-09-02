if(pk_val != '') {print_memo_list(pk_val);};

async function get_memo_list_from_server(pk_val) {
    try {
        const resp = await fetch('/ch/list/' + pk_val);
        const memo_list = await resp.json();
        return await memo_list;
    } catch (error) {
        console.log(error);
    }
}

async function reg_channel_to_server(reg_data) {
    try {
        const url = "/ch/reg_ch";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(reg_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function reg_post_data_to_server(reg_data) {
    try {
        const url = "/ch/reg_memo";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(reg_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function reg_image_data_to_server(form_data) {
    try {
        const url = "/ch/reg_image";
        const config = {
            method: 'post',
            body: form_data
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function get_info_from_server() {
    try {
        const resp = await fetch("/ch/info/" + pk_val);
        const info_data = await resp.json();
        return await info_data;
    } catch (error) {
        console.log(error);
    }
}

// image 메모 create 실행
document.getElementById('image_reg_btn').addEventListener('click', () => {
    const file_name = document.getElementById('image_file').value;
    if (file_name != "" && file_name != null) {
        const form_data = new FormData();
        form_data.append("cno", pk_val);
        form_data.append("image_file", document.getElementById('image_file').files[0]);
        reg_image_data_to_server(form_data).then(result => {
            console.log(result);
            if(result > 0) {
                print_memo_list(pk_val);
                close_all_modal();
            } else {
                alert("생성 중에 오류가 발생하였습니다.");
                location.reload();
            }
        });
    } else {
        alert("파일이 없습니다!");
    }
});

document.querySelector('#ch_title > h1 > .bi-house').addEventListener('click', () => {
    location.replace("/main/me")
});

document.getElementById('reload_btn').addEventListener('click', () => {
    print_memo_list(pk_val);
});

// post 메모 create 실행
function create_post_memo(color) {
    if(pk_val == '') {
        const reg_data = {
            "ch_name": ch_name,
            "color": color
        }
        reg_channel_to_server(reg_data).then(result => {
            if (result < 1) {
                alert("오류가 발생하였습니다.(64)");
            }
            location.reload();
        })
    } else {
        const reg_data = {
            "cno": pk_val,
            "color": color
        }
        reg_post_data_to_server(reg_data).then(result => {
            if (result > 0) {
                print_memo_list(pk_val);
            } else {
                alert("오류가 발생하였습니다.(77)");
                location.reload();
            }
        });
    }
}

function print_info_data(info_data) {
    document.getElementById('ch_info').innerHTML = 
    `<ul class="my-4 mx-2 px-4">
    <li>인원수: ${info_data.head_count}명</li>
    <li>총 메모 수: ${info_data.memo_count}개</li>
    <li>개설일: ${info_data.created.substring(2, 16)}</li>
    <li>마지막 사용일: ${info_data.last_used.substring(2, 16)}</li>
    </ul>`;
}

function print_memo_list(pk_val) {
    get_memo_list_from_server(pk_val).then (result => {
        console.log(result);
        const print_area = document.getElementById('memobox');
        print_area.innerHTML = "";
        if (document.getElementById('sort_created').checked) {
            result.sort(function(a, b){return Date.parse(b.created) - Date.parse(a.created)});
        } else {
            result.sort(function(a, b){return Date.parse(b.modified) - Date.parse(a.modified)});
        }
        if (document.getElementById('sort_asc').checked) {
            result.reverse();
        }
        result.forEach((el) => {
            if (el.uno == uno_val || (document.getElementById('view_empty').checked || (el.content != null && el.content != ""))) {
                let str = "";
                str += `<div class="memo memo-${el.color} m-3" data-mno="${el.mno}">`;
                str += `<div class="memo-header px-2 py-1 text-end">`;
                str += `<div class="col-6 float-start px-1 text-start text-muted fst-italic">`;
                str += `${el.nick_name}</div>`;
                str += `<div class="col-6 float-end">`;
                str += `<i class="bi bi-info-square memo-info-btn"></i>`;
                if (el.uno == uno_val) {
                    str += `<i class="bi bi-pencil-square update-btn"></i>`;
                    str += `<i class="bi bi-x-square delete-btn"></i>`;
                }
                str += `</div></div>`;
                str += `<div class="memo-section position-relative">`;
                str += `<div class="memo-content ${el.types == "image" ? "overflow-hidden" : "overflow-scroll"} p-3">`;
                if (el.types == "image") {
                    str += `<img src="/_fileUpload/th_${el.content}" alt="${el.content}">`;
                } else {
                    str += `${el.content == null ? "" : el.content.replace(/(?:\r\n|\r|\n)/g, '<br />')}`;
                }
                str += `</div>`;
                str += `<div class="memo-info position-absolute top-0 opacity-0 pe-none">`;
                str += `<ul class="position-absolute top-50 translate-middle-y fs-5 text-white">`;
                str += `<li>생성일: ${el.created.substring(2, 16)}</li>`;
                str += `<li>수정일: ${el.modified.substring(2, 16)}</li>`;
                str += `</ul></div></div>`;
                print_area.innerHTML += str;
            }
        })
        document.getElementById('ch_info').innerHTML = `<div class="my-4 mx-2 px-4"><div class="spinner-border text-light"></div></div>`;
        info_trigger = true;
    })
}