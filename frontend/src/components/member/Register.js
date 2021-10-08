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
const div =styled.div`
    padding-left: 0.5rem;
    padding-right: 0.5rem;
    width : 80%;
`;
const Register = () => {
  return (
    <>
          <div>
                <label>비밀번호:</label>
                <Input placeholder={'비밀번호'} />
          </div>
          <div>
                <label>비밀번호 확인:</label>
                <Input placeholder={'비밀번호 확인'} />
          </div>
          <div>
                <label>이메일:</label>
                <Input placeholder={'이메일'}/>
          </div>
          <div>
                <label>핸드폰 번호:</label>
                <Input placeholder={'핸드폰 번호'}/>
          </div>
          <div>
                <label>성별:</label>
                <Input placeholder={'성별'}/>
          </div>
          <div>
                <label>나이:</label>
                <Input placeholder={'나이'}/>
          </div>
          
          <Button>회원가입</Button>
    </>
  );
};

export default Register;
