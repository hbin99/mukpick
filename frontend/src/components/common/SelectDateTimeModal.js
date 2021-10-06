import styled from 'styled-components';
import Button from './Button';
import DatePicker from 'react-datepicker';
import { ko } from 'date-fns/esm/locale';
import 'react-datepicker/dist/react-datepicker.css';
import { useState } from 'react';
const ModalHeader = styled.div`
  width: 100%;
  background: #df734a;
  padding: 5px;
  box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.125);
  font-size: 20px;
  font-weight: bold;
`;

const ModalBody = styled.div`
  width: 100%;
  background: white;
  padding: 0.5rem;
`;

const ModalFooter = styled.div`
  width: 100%;
`;

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

const SelectDateTimeBlock = styled.div`
  width: 320px;
  background: white;
  border-radius: 4px;
  box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.125);
  h2 {
    margin-top: 0;
    margin-bottom: 1rem;
  }

  .buttons {
    display: flex;
    justify-content: flex-end;
  }
`;

const StyledButton = styled(Button)`
  height: 2rem;
  float: right;
  margin: 0.5rem;
`;

const SelectDatePicker = styled(DatePicker)`
  width: 250px;
  margin-left: 25px;
`;

const SelectDateTimeModal = ({
  visible,
  title,
  description,
  validDate,
  confirmText = '변경',
  onConfirm,
  changeShowConfirm,
  showTimeSelect = true,
}) => {
  const [selectedDate, setSelectedDate] = useState(new Date(validDate));

  const onSelectDate = () => {
    onConfirm(selectedDate);
  };
  if (!visible) return null;
  return (
    <Fullscreen>
      <SelectDateTimeBlock>
        <ModalHeader>{title}</ModalHeader>
        <ModalBody>
          <h6>{description}</h6>
          <SelectDatePicker
            selected={selectedDate}
            onChange={(date) => setSelectedDate(date)}
            showTimeSelect={showTimeSelect}
            timeFormat="HH:mm"
            timeCaption="time"
            dateFormat="yyyy년 MM월 dd일 HH:mm:ss"
            locale={ko}
          />
        </ModalBody>
        <ModalFooter>
          <StyledButton onClick={changeShowConfirm}>취소</StyledButton>
          <StyledButton cyan onClick={onSelectDate}>
            {confirmText}
          </StyledButton>
        </ModalFooter>
      </SelectDateTimeBlock>
    </Fullscreen>
  );
};

export default SelectDateTimeModal;
