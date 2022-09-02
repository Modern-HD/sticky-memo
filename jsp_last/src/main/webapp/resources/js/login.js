const reg = {
    email: /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/,
    nick: /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/,
    pwd: /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
}

const tooltip = {
    d_login: document.getElementById('l_danger'),
    d_sign:  document.getElementById('s_danger'),
    t_sign: document.getElementById('s_tooltip')
}

async function sign_up_to_server(user_data) {
    try {
        const url = "/user/signup";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(user_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (err) {
        console.log(err);
    }
}

async function login_to_server(login_data) {
    try {
        const url = "/user/login";
        const config = {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(login_data)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (err) {
        console.log(err);
    }
}

document.getElementById('to_sign_up_form').addEventListener('click', change_form);
document.getElementById('to_login_form').addEventListener('click', change_form);

document.getElementById('sign_up_btn').addEventListener('click', sign_up);
document.getElementById('login_btn').addEventListener('click', login);

document.querySelectorAll('#sign_up_form input').forEach((el) => {
    switch (el.id) {
        case "s_nick_name":
            el.addEventListener('focus', () => {
                tooltip.t_sign.innerHTML = "2 ~ 10자의 한글, 영어, 숫자 사용 가능"
            });
            break;
        case "s_pwd":
            el.addEventListener('focus', () => {
                tooltip.t_sign.innerHTML = "영문과 숫자, 특수문자를 모두 포함한 8 ~ 16자 사용"
            });
            break;
        default:
            el.addEventListener('focus', () => {
                tooltip.t_sign.innerHTML = ""
            });
            break;
    }
})


function change_form() {
    document.querySelectorAll('.form-modal').forEach((el) => {
        el.classList.toggle('d-none');
    })
}

function login() {
    let input_email = document.getElementById('l_email');
    let input_pwd = document.getElementById('l_pwd');
    let login_data = {
        email: input_email.value,
        pwd: input_pwd.value
    }
    login_to_server(login_data).then(result => {
        console.log(result);
        const msg = JSON.parse(result);
        if (msg.result > 0) {
            alert(msg.msg);
            location.replace("/main/me");
        } else {
            tooltip.d_login.innerHTML = `${msg.msg}`;
        }
    })
}

function sign_up() {
    let sign_up_btn = document.getElementById('sign_up_btn');
    sign_up_btn.disabled = true;
    let input_email = document.getElementById('s_email');
    let input_nick = document.getElementById('s_nick_name');
    let input_pwd = document.getElementById('s_pwd');
    let input_pwd_chk = document.getElementById('s_pwd_chk');
    if (!reg.email.test(input_email.value)) {
        tooltip.d_sign.innerHTML = `올바른 이메일을 입력해주세요.`;
        input_email.value = "";
        input_email.focus();
        sign_up_btn.disabled = false;
        return;
    } else if(!reg.nick.test(input_nick.value)) {
        tooltip.d_sign.innerHTML = `입력 조건을 확인해주세요.`;
        input_nick.value = "";
        input_nick.focus();
        sign_up_btn.disabled = false;
        return;
    } else if(!reg.pwd.test(input_pwd.value)) {
        tooltip.d_sign.innerHTML = `입력 조건을 확인해주세요.`;
        input_pwd.value = "";
        input_pwd_chk.value = "";
        input_pwd.focus();
        sign_up_btn.disabled = false;
        return;
    } else if (input_pwd.value != input_pwd_chk.value) {
        tooltip.d_sign.innerHTML = `비밀번호가 일치하지 않습니다.`;
        input_pwd.value = "";
        input_pwd_chk.value = "";
        input_pwd.focus();
        sign_up_btn.disabled = false;
        return;
    }
    let user_data = {
        email: input_email.value,
        nick_name: input_nick.value,
        pwd: input_pwd.value
    }
    sign_up_to_server(user_data).then(result => {
        const msg = JSON.parse(result);
        console.log(msg);
        if (msg.result > 0) {
            alert("회원가입 성공!");
            document.getElementById('l_email').value = input_email.value;
            document.querySelectorAll('#sign_up_form input').forEach( (el) => {
                el.value = "";
            })
            change_form();
        } else {
            tooltip.d_sign.innerHTML = `${msg.msg}`;
        }
        sign_up_btn.disabled = false;
    });
}