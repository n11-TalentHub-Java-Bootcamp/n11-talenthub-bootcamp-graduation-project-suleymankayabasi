import React, { useState } from 'react';

const QueryForm = () => {

  const [nationalId, setNationalId] = useState("");
	const [birthDay, setBirthDay] = useState("");
	const [approvalStatus, setApprovalStatus] = useState("");
	const [createdAt, setCreatedAt] = useState("");
	const [creditLimit, setCreditLimit] = useState("");
	
	const handleQuery = (e) => {
		e.preventDefault();

		fetch(
			`http://localhost:8080/credit/get?nationalId=${nationalId}&birthDay=${birthDay}`,
			{
				method: "GET",
			}
		).then((res) => {
			res.status === 200 ? alert("Kredi Sorgulama Başarılı") : alert("Kredi Sorgulama Başarısız");
			return res.json();
		}).then((data) => {
			
			setApprovalStatus(data.reverse()[0].approvalStatus);
			setCreatedAt(data.reverse()[0].createdAt);
			setCreditLimit(data.reverse()[0].creditLimit);
		});
	};


  return (
		<div className='bg-white min-h-screen w-full flex flex-col'>
			<form className='mx-auto mt-40'>
      <h3 className='block w-full text-center text-grey-darkest rounded-md font-bold uppercase bg-teal-500 py-2 mb-6'>
				Kredi Sorgulama Formu
			</h3>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 mr-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='nationalId'
					>
						T.C. Kimlik No
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='nationalId'
						id='nationalId'
						value={nationalId}
						onChange={(e) => setNationalId(e.target.value)}
					/>
				</div>
				<div className='flex flex-col mb-6'>
					<label
						className='mb-2 mr-2 uppercase font-bold text-lg text-grey-darkest'
						htmlFor='birthDay'
					>
						Doğum Tarihi
					</label>
					<input
						className='border py-2 px-3 text-grey-darkest'
						type='text'
						name='birthDay'
						id='birthDay'
						value={birthDay}
						onChange={(e) => setBirthDay(e.target.value)}
					/>
				</div>
				<button
					className='block bg-teal-500 hover:bg-teal-dark text-white uppercase text-lg mx-auto p-4 rounded'
					type='submit'
					onClick={(e) => {
						handleQuery(e);
					}}
				>
					Sorgula
				</button>
			</form>
			<div className='flex flex-col justify-center mx-auto mt-6'>
				<div className='flex flex-row mt-2'>
					<h1>Onay Durumu : </h1>
					<p> {approvalStatus ? " ONAYLANDI " : ""}</p>
				</div>
				<div className='flex flex-row mt-2'>
					<h1>Başvuru Tarihi : </h1>
					<p> {createdAt}</p>
				</div>
				<div className='flex flex-row mt-2'>
					<h1>Kredi Limiti : </h1>
					<p> {creditLimit}</p>
				</div>
			</div>
		</div>
	);
};

export default QueryForm;
