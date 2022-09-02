print_memo_list(pk_val);

async function get_memo_list_from_server(pk_val) {
    try {
        const resp = await fetch('/mine/list/' + pk_val);
        const memo_list = await resp.json();
        return await memo_list;
    } catch (error) {
        console.log(error);
    }
}

async function reg_post_data_to_server(color) {
    try {
        const resp = await fetch('/mine/register/' + color);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function reg_image_data_to_server(form_data) {
    try {
        const url = "/mine/register/image";
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
        const resp = await fetch("/mine/info/" + pk_val);
        const info_data = await resp.json();
        return await info_data;
    } catch (error) {
        console.log(error);
    }
}

// post 메모 create 실행
function create_post_memo(color) {
    reg_post_data_to_server(color).then(result => {
        if (result > 0) {
            print_memo_list(pk_val);
        } else {
            alert("생성 중에 오류가 발생하였습니다.");
            location.reload();
        }
    });
}

// image 메모 create 실행
document.getElementById('image_reg_btn').addEventListener('click', () => {
    const file_name = document.getElementById('image_file').value;
    if (file_name != "" && file_name != null) {
        const form_data = new FormData();
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
})

function print_info_data(info_data) {
    document.getElementById('ch_info').innerHTML = 
    `<ul class="my-4 mx-2 px-4">
    <li>총 메모 수: ${info_data.memo_count}개</li>
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
            let str = "";
            str += `<div class="memo memo-${el.color} m-3" data-mno="${el.mno}">`;
            str += `<div class="memo-header px-2 py-1 text-end">`;
            str += `<div class="col-6 float-end">`;
            str += `<i class="bi bi-info-square memo-info-btn"></i>`
            str += `<i class="bi bi-pencil-square update-btn"></i>`;
            str += `<i class="bi bi-x-square delete-btn"></i></div></div>`;
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
            document.getElementById('ch_info').innerHTML = `<div class="my-4 mx-2 px-4"><div class="spinner-border text-light"></div></div>`;
            info_trigger = true;
        })
    })
}