"use client";
import { usePathname } from "next/navigation";
import { memo } from "react";

const CheckoutProgress = () => {
  const pathname = usePathname();
  console.log("pathname", pathname);
  return (
    <div className="flex items-center-safe justify-center md:justify-start gap-4 md:gap-8">
      <div className="flex flex-col items-center gap-2">
        <div
          className={`inline-flex flex-col items-center justify-center rounded-full p-[12px] ${
            pathname === "/checkout/confirmInfo"
              ? "bg-green-600"
              : "bg-red-500 "
          }`}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            className="text-white"
          >
            <g fill="none">
              <path
                d="M12 2a8 8 0 0 1 8 8c0 6.5-8 12-8 12s-8-5.5-8-12a8 8 0 0 1 8-8m0 5a3 3 0 1 0 0 6a3 3 0 0 0 0-6"
                clipRule="evenodd"
              />
              <path
                stroke="currentColor"
                strokeWidth="2"
                d="M20 10c0 6.5-8 12-8 12s-8-5.5-8-12a8 8 0 1 1 16 0Z"
              />
              <path
                stroke="currentColor"
                strokeWidth="2"
                d="M15 10a3 3 0 1 1-6 0a3 3 0 0 1 6 0Z"
              />
            </g>
          </svg>
        </div>
        <p className="text-[14px] max-w-[100px] text-center font-semibold">
          Thông tin giao hàng
        </p>
      </div>
      <div
        className={`hidden md:block w-24 h-1 bg-gray-200 mb-10 ${
          pathname === "/checkout/confirmInfo" ? "bg-green-600" : "bg-gray-200"
        }`}
      ></div>
      <div className="flex flex-col items-center gap-2">
        <div
          className={`inline-flex flex-col items-center justify-center bg-gray-300 rounded-full p-[12px] ${
            pathname === "/checkout" ? "bg-gray-300" : "bg-red-500"
          }`}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 15 15"
            className={`text-gray-600 ${
              pathname === "/checkout" ? "text-gray-600" : "text-white"
            }`}
          >
            <path
              fill="none"
              stroke="currentColor"
              d="M4 7.5L7 10l4-5m-3.5 9.5a7 7 0 1 1 0-14a7 7 0 0 1 0 14Z"
            />
          </svg>
        </div>
        <p className="text-[14px] max-w-[100px] text-center font-semibold text-gray-500">
          Xác nhận đơn hàng
        </p>
      </div>
    </div>
  );
};

export default memo(CheckoutProgress);
