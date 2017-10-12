/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { OutsiderTradingTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { UserPickDetailComponent } from '../../../../../../main/webapp/app/entities/user-pick/user-pick-detail.component';
import { UserPickService } from '../../../../../../main/webapp/app/entities/user-pick/user-pick.service';
import { UserPick } from '../../../../../../main/webapp/app/entities/user-pick/user-pick.model';

describe('Component Tests', () => {

    describe('UserPick Management Detail Component', () => {
        let comp: UserPickDetailComponent;
        let fixture: ComponentFixture<UserPickDetailComponent>;
        let service: UserPickService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [OutsiderTradingTestModule],
                declarations: [UserPickDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    UserPickService,
                    JhiEventManager
                ]
            }).overrideTemplate(UserPickDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserPickDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserPickService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new UserPick(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.userPick).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
