import { useDispatch, useSelector } from 'react-redux';
import member, {initializeForm, chengeField, findId,findPassword } from '../../modules/member/findUser';
import { withRouter } from 'react-router-dom';
import FindUser from '../../components/member/FindUser';
import {useEffect, useState} from "react";

const FindUserPageContainer = ({type}) => {
  const dispatch = useDispatch();
  const [vaildError,setVaildError] = useState(null);
  const { resultMsg, error, form,loading } = useSelector(({member,loading }) => ({
      form :member,
      resultMsg: member.resultMsg,
      error: member.error,
      loading: loading['/member/'+type ],
      }),
  );
  const onClick = (e) => {
        if(type === 'findId'){
            if(form.findId.userName === ''){
                setVaildError('회원 이름을 입력해주세요.');
                return;
            } 
            if(form.findId.email === ''){
                setVaildError('이메일를 입력해주세요.');
                return;
            }
            if(form.findId.phone === ''){
                setVaildError('핸드폰 번호를 입력해주세요.');
                return;
            }
            dispatch(findId( form.findId));

        }else if(type === 'findPassword'){
            if(form.findPassword.userId  === ''){
                setVaildError('아이디를 입력해주세요.');
                return;
            }
            if(form.findPassword.email  === ''){
                setVaildError('이메일를 입력해주세요.');
                return;
            }
            if(form.findPassword.phone  === ''){
                setVaildError('핸드폰 번호를 입력해주세요.');
                return;
            }
            dispatch(findPassword(form.findPassword));
        }
  };
  const onChange = (e) => {
    const {value,name} = e.target;
    if(vaildError){
        setVaildError(null);
    }
    dispatch(
      chengeField({
          form: type,
          key:name,
          value
      })
    );
  };
  useEffect(()=>{
      dispatch(initializeForm(type));
  },[dispatch]);


    return (
    <>
      <FindUser
        type={type}
        form={form}
        loading={loading}
        error = {error}
        error = {vaildError}
        resultText={resultMsg}
        onClick={onClick}
        onChange={onChange}
      />
    </>
  );
};

export default withRouter(FindUserPageContainer);
