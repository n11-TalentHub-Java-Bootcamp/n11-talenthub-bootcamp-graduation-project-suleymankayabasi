import React from "react";
import { Link } from "react-router-dom";

// side navigation bar component
const Sidebar = () => {
	return (
		<div className='w-2/6 flex flex-col bg-white mx-auto'>
			<div className='mt-4 '>
				<div>
					<div className='text-center px-4 mb-4'>
						<Link to='application'>
							<h1 className='text-bold font-xl py-2 px-4 bg-gray-800 text-white rounded-md hover:bg-teal-700 cursor-pointer'>
								Başvuru Formu
							</h1>
						</Link>
					</div>
					<div className='text-center px-4 mb-4'>
						<Link to='actions'>
							<h1 className='text-bold font-xl py-2 px-4 bg-gray-800 text-white rounded-md hover:bg-teal-700 cursor-pointer'>
								Kullanıcı İşlemleri
							</h1>
						</Link>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Sidebar;
