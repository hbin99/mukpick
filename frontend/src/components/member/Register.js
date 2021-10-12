import styled from 'styled-components';
import Button from "../common/Button";
import React from "react";

const Input = styled.input`
    outline: none;
    border-radius: 0px;
    line-height: 2.5rem;
    font-size: 1.2rem;
    padding-left: 0.5rem;
    padding-right: 0.5rem;
`;
const div =styled.div`
    padding-left: 0.5rem;
    padding-right: 0.5rem;
    width : 80%;
`;

const SearchTextItem = ({ item }) => {
    const { RESULT_MSG, RESULT_FLAG } = item;
    return (
        <div>
            <div>{RESULT_MSG} </div>
            <div>{RESULT_FLAG} </div>
        </div>
    );
};
const textMap = {
    register : '회원가입',
}
const RegisterForm = ({type, loading,form, resultText , onChange, onCheck , onRegister}) => {
    const text = textMap[type];
    return (
    <>
        <h3>{text}</h3>
        <div>
            <label>아이디:</label>
            <Input placeholder={'아이디'}
                   name = "userId"
                   onChange={onChange}
                   value={form.userId}
            />
            <Button> 아이디 중복 확인</Button>
        </div>
        <div>
            <label> 이름 :</label>
            <Input placeholder={'이름'}
                   name = "userName"
                   onChange={onChange}
                   value={form.userName}
            />
        </div>
        <div>
            <label>비밀번호:</label>
            <Input placeholder={'비밀번호'}
                   name = "userPassword"
                   onChange={onChange}
                   value={form.userPassword}
            />
        </div>
        <div>
            <label>비밀번호 확인:</label>
            <Input placeholder={'비밀번호 확인'}
                   name = "userPasswordConfirm"
                   onChange={onChange}
                   value={form.userPasswordConfirm}
            />
        </div>
        <div>
            <label>이메일:</label>
            <Input placeholder={'이메일'}
                   name = "email"
                   onChange={onChange}
                   value={form.email}
            />
        </div>
        <div>
            <label>핸드폰 번호:</label>
            <Input placeholder={'핸드폰 번호'}
                   name = "phone"
                   onChange={onChange}
                   value={form.phone}
            />
        </div>
        <div>
            <label>성별:</label>
            <Input placeholder={'성별'}
                   name = "gender"
                   onChange={onChange}
                   value={form.gender}
            />
        </div>
        <div>
            <label>나이:</label>
            <Input placeholder={'나이'}
                   name = "age"
                   onChange={onChange}
                   value={form.age}
            />
        </div>
        <Button onClick={onRegister}>회원가입</Button>

        {!loading && resultText && <SearchTextItem item={resultText} />}
    </>
  );
};

export default RegisterForm;
