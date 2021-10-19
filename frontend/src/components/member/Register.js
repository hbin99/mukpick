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
const ErrorMsg = styled.div`
    color :red;
    margin-top : 1rem;
`

const div =styled.div`
    padding-left: 0.5rem;
    padding-right: 0.5rem;
    width : 80%;
`;

const SearchTextItem = ({ item }) => {
    const { RESULT_MSG, RESULT_FLAG } = item;
    return (
        <div>
            { RESULT_FLAG === 'ERROR' ?
                (
                    <ErrorMsg>{RESULT_MSG}</ErrorMsg>
                ):(
                    <div>{RESULT_MSG} </div>
                )}
        </div>
    );
};
const textMap = {
    register : '회원가입',
}
const RegisterForm = ({type, loading,form, resultText , onChange, onCheck , onClick, onblur,error }) => {
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
            <Button onClick ={onCheck}> 중복 확인</Button>
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
                   type ="password"
                   value={form.userPassword}
            />
        </div>
        <div>
            <label>비밀번호 확인:</label>
            <Input placeholder={'비밀번호 확인'}
                   name = "userPasswordConfirm"
                   onChange={onChange}
                   type ="password"
                   value={form.userPasswordConfirm}
                   onBlur ={onblur}
            />
        </div>
        <div>
            <label>이메일:</label>
            <Input placeholder={'이메일'}
                   name = "email"
                   onChange={onChange}
                   value={form.email}
                   onBlur ={onblur}
            />
        </div>
        <div>
            <label>핸드폰 번호:</label>
            <Input placeholder={'핸드폰 번호'}
                   name = "phone"
                   onChange={onChange}
                   value={form.phone}
                   onBlur ={onblur}
            />
        </div>
        <div>
            <label>성별:</label>
            <label>남</label>
            <Input placeholder={'성별'}
                   name = "gender"
                   onChange={onChange}
                   value={form.gender}
                   type ="radio"
            />
            <label>여</label>
            <Input placeholder={'성별'}
                   name = "gender"
                   onChange={onChange}
                   value={form.gender}
                   type ="radio"
            />
        </div>
        <div>
            <label>나이:</label>
            <Input placeholder={'나이'}
                   name = "age"
                   type ="number"
                   onChange={onChange}
                   value={form.age}
            />
        </div>
        <Button onClick={onClick}>회원가입</Button>
        {error && <ErrorMsg>{error}</ErrorMsg> }
        {!loading && !error && resultText && <SearchTextItem item={resultText} />}
    </>
  );
};

export default RegisterForm;
