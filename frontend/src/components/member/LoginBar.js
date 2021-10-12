import styled from 'styled-components';
import Button from "../common/Button";

const Input = styled.input`
    outline: none;
    border-radius: 0px;
    line-height: 2.5rem;
    font-size: 1.2rem;
    padding-left: 0.5rem;
    padding-right: 0.5rem;
`;

const LoginBar = () => {
  return (
    <>
        <Input placeholder='아이디' />
        <Input placeholder='비밀번호' />
        <Button>로그인</Button>
        <Button to="/find-id">아이디찾기</Button>
        <Button to="/find-password">비밀번호 찾기</Button>
    </>
  );
};

export default LoginBar;
