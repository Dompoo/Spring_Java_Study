package Dompoo.SpringCore.order;

import Dompoo.SpringCore.discount.DiscountPolicy;
import Dompoo.SpringCore.discount.FixDiscountPolicy;
import Dompoo.SpringCore.member.Member;
import Dompoo.SpringCore.member.MemberRepository;
import Dompoo.SpringCore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //여기서 다른 DiscountPolicy 구현체로 갈아끼울려면 OCP, DIP가 위반된다.
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member findMember = memberRepository.findById(memberId);
        int discountAmount = discountPolicy.discount(findMember, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountAmount);
    }
}