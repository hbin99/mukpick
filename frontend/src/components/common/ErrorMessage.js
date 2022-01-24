const ErrorMessage = ({ error }) => {
  const { status, message, code } = error;
  return (
    <>
      <ul>
        <li>
          <h1>
            {status} {code}
          </h1>
        </li>
        <li>{message}</li>
      </ul>
    </>
  );
};

export default ErrorMessage;
