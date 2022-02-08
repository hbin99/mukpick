import styled from 'styled-components';
import Button from './Button';
import { useState } from 'react';

const Fullscreen = styled.div`
  position: fixed;
  z-index: 30;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.25);
  display: flex;
  justify-content: center;
  align-items: center;
`;

const InputModalBlock = styled.div`
  width: 320px;
  background: white;
  padding: 1.5rem;
  border-radius: 4px;
  box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.125);
  h2 {
    margin-top: 0;
    margin-bottom: 1rem;
  }
  .input {
    margin-bottom: 1rem;
    width: 100%;
    height:35px;
  }
  .buttons {
    display: flex;
    justify-content: flex-end;
  }
`;

const StyledButton = styled(Button)`
  height: 2rem;
  & + & {
    margin-left: 0.75rem;
  }
`;

const InputModal = ({
  visible,
  title,
  description,
  inputText,
  confirmText = '확인',
  cancelText = '취소',
  onSave,
  onCancel,
}) => {
  const [text, setText] = useState(inputText);

  const onChange = (e) => {
    e.preventDefault();
    setText(e.target.value);
  }

  const onEnter = (e) => {
    if(e.code === 'Enter'){
      inputSave();
    };
  }

  const onClose = () => {
    setText(inputText);
    onCancel();
  }

  const inputSave = () => {
    onSave(text ? text : inputText);
    onCancel();
  }

  if (!visible) return null;



  return (
    <Fullscreen>
      <InputModalBlock>
        <h2>{title}</h2>
        <p>{description}</p>
        <input type={'text'} className={"input"} value={text} onChange={onChange} onKeyPress={onEnter}/>
        <div className="buttons">
          <StyledButton onClick={onClose}>{cancelText}</StyledButton>
          <StyledButton cyan onClick={inputSave}>
            {confirmText}
          </StyledButton>
        </div>
      </InputModalBlock>
    </Fullscreen>
  );
};

export default InputModal;
